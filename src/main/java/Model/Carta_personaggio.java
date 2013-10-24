package Model;

import java.io.File;
import java.io.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Modellizza le carte personaggio dal gioco.
 *
 */
public class Carta_personaggio extends Carta implements Serializable {

	
	private String Nome_carta;
	private int soldi;
	private String scuderia;  
	private String immagine;
	private String retro;
	private static final String path_carte="immagini_carte_personaggi/";
	
	/**
	 * @param nc = nome della carta 
	 * @param s = soldi bonus all'inizio della partita  
	 * @param scud = proprietario della scuderia iniziale scud ( quotazione ) 
	 */
	
	public Carta_personaggio(String nc , int s , int scud , String path_immagine)
	{
	
	 super();
	 Lavagna lavagna =Lavagna.creaLavagna();
	 this.Nome_carta = nc;
	 this.soldi= s;
	 this.scuderia = lavagna.getColore(scud);
	 this.immagine = path_immagine;
	 this.retro = path_carte+"retro.jpg";
	}
	
	public int  get_soldi() {
		return this.soldi;	
	}
	
	public String getNome()
	{
		return this.Nome_carta;
	}
	
	public String get_scuderia() {
		return this.scuderia;	
	}
	
	public String getImmagine() {
		return this.immagine;
	}
	
	public void visualizza()
	{
	System.out.println("Nome_carta: --> "+ this.Nome_carta);
	System.out.println("Soldi --> "+this.soldi);
	System.out.println("Scuderia: --> "+this.scuderia);
	}
	

	
	/**
	 * Implementazione in xml delle carte
	 * Metodo utilizzato per estrarre tutte le carte personaggio dal file 
	 * e creare il mazzo di carte personaggio
	 * @param m Mazzo in cui inserisco carte
	 */
	public static void Extract_all_in(Mazzo m) {
		try{	
			File file = new File("CarteXml/Carte_personaggio.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
			Document document = docBuilder.parse(file);
			
			document.getDocumentElement().normalize();
			NodeList  listNode= document.getElementsByTagName("Carta");
			
			for (int i=0;i<listNode.getLength();i++)
			{
				Node node=listNode.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String nome = element.getElementsByTagName("nome").item(0).getTextContent();
					int denaro = Integer.parseInt(element.getElementsByTagName("denaro").item(0).getTextContent());
					int quotazione = Integer.parseInt(element.getElementsByTagName("quotazione").item(0).getTextContent());
					String immagine = path_carte + element.getElementsByTagName("Immagine").item(0).getTextContent();
					Carta card = new Carta_personaggio(nome,denaro,quotazione , immagine);
		          	 m.addCard(card);

				}
			}
			 m.mischia(); // diamo una mischiata al mazzo corrente 	
		}
		 catch (Exception e) {
				e.printStackTrace();
			    }
	}
}
