import java.io.*;  //librerie
import java.net.*;

public class ClientStr {

    

    String nomeServer= "localhost";  //indirizzo server
    int portaServer = 6789; //porta servizio
    Socket miosocket;
    BufferedReader tastiera;
    String stringUtente;
    String stringRicevutaDalServer;
    DataOutputStream outVersoServer;  //stream output
    BufferedReader inDalServer;

public Socket connetti ()
{
 System.out.println("Client in esecuzione");
    try{
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        miosocket = new Socket(nomeServer,portaServer);
        outVersoServer = new DataOutputStream(miosocket.getOutputStream());
        inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
    }
    catch (UnknownHostException e) {
        System.err.println("Host sconosciuto");}
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }

    

    public void comunica(){
        try{
            System.out.println("4 ... inserisci la stringa da trasmettere al server:"+'\n');
            String stringaUtente = tastiera.readLine();
            System.out.println("5 ... invio la stringa al server...");
            outVersoServer.writeBytes(stringaUtente+'\n');
            String stringaRicevutaDalServer=inDalServer.readLine();
            System.out.println("8 ... risposta del server"+'\n'+stringaRicevutaDalServer);
            System.out.println("9 Client:termina elaborazione e chiude connessione");
            miosocket.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione del server");
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        ClientStr cliente = new ClientStr();
        cliente.connetti();
        cliente.comunica();
    }
}