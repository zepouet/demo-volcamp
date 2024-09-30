///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS org.apache.sshd:sshd-scp:2.13.2
//DEPS org.apache.sshd:sshd-core:2.13.2
//DEPS org.apache.sshd:sshd-common:2.13.2

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.scp.client.ScpClient;
import org.apache.sshd.scp.client.ScpClientCreator;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static java.lang.foreign.ValueLayout.JAVA_BOOLEAN;

@Command(name = "deploy", mixinStandardHelpOptions = true, version = "Deployment 0.1",
        description = "Oldschool Deployment made with jbang")
class helloworld implements Callable<Integer> {

    @Parameters(index = "0", description = "the file to copy with ssh", defaultValue = "servlet.jsp")
    private String greeting;

    public static void main(String... args) {
        try {
            //System.load("/Users/nicolas/projects/demo-volcamp/panama-watch/ffm-touchid/build/resources/main/libTouchIdDemoLib.dylib");
            System.load("/Users/nicolas/projects/demo-volcamp2/panama-watch/touchid-swift-lib/build/lib/main/release/shared/macos/arm64/libTouchIdDemoLib.dylib");
            //System.loadLibrary("TouchIdDemoLib");

            var authenticate_user = Linker.nativeLinker().downcallHandle(
                    SymbolLookup.loaderLookup().find("authenticate_user_touchid").orElseThrow(),
                    FunctionDescriptor.of(JAVA_BOOLEAN)
            );

            Boolean pass = (boolean) authenticate_user.invokeExact();
            if (pass) {
                System.out.println("> You may enter!");
                System.out.println("> You may enter!");
                System.out.println("> You may enter!");
            } else {
                System.out.println("> Access Denied ");
                System.out.println("> Access Denied ");
                System.out.println("> Access Denied ");
                System.exit(-1);
            }
        } catch (Throwable e) {
            System.exit(-1);
        }
        int exitCode = new CommandLine(new helloworld()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        System.out.println("Deploy  " + greeting + " with ssh on the server");
        copy(null);
        return 0;
    }

    public static void copy(String[] args) {
        // Démarrer un client SSH
        SshClient client = SshClient.setUpDefaultClient();
        client.start();

        try (ClientSession session = client.connect("root", "localhost", 2222)
                .verify(10, TimeUnit.SECONDS)
                .getSession()) {

            // Authentification par mot de passe
            session.addPasswordIdentity("root123");
            session.auth().verify(10, TimeUnit.SECONDS);

            // Créer un client SCP pour la session
            ScpClientCreator creator = ScpClientCreator.instance();
            ScpClient scpClient = creator.createScpClient(session);

            // Chemin du fichier local et destination distante
            String localFilePath = "/Users/nicolas/projects/demo-volcamp/mycli/README.md";
            String remoteDirPath = "/root/";

            // Transférer le fichier
            scpClient.upload(Paths.get(localFilePath), remoteDirPath);

            System.out.println("Transfert SCP réussi pour le fichier : " + localFilePath);

        } catch (Exception e) {
            System.err.println("Erreur lors du transfert SCP : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Arrêter le client SSH proprement
            client.stop();
        }
    }

}
