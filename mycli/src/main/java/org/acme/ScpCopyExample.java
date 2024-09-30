//DEPS org.apache.sshd:sshd-core:2.10.0
//DEPS org.apache.sshd:sshd-scp:2.10.0
//DEPS org.apache.sshd:sshd-common:2.10.0

package org.acme;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.scp.client.ScpClient;
import org.apache.sshd.scp.client.ScpClientCreator;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ScpCopyExample {
    public static void main(String[] args) {
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
