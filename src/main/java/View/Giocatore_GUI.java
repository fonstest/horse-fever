package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Controller_events.getNumPlayerEvent;
import Gui_Events.setNumPlayerEvent;

public class Giocatore_GUI {

	GUI_interface gui_interface;

	private final Toolkit toolkit ;
	private int xSize;
	private int ySize;
	private JFrame frame;
	private  getNumPlayerEvent event1;
	int num_giocatori_temp;
	JPanel top_panel;
	JPanel main_panel;
	JPanel bottom_panel;
	//---------ATTRIBUTI METODI-------> 
	private JLabel image_num_player;


	
	public  Giocatore_GUI(GUI_interface gui , getNumPlayerEvent event) {
		
		this.event1 = event;
		toolkit = Toolkit.getDefaultToolkit();
		xSize = ((int) toolkit.getScreenSize().getWidth());  
		ySize = ((int) toolkit.getScreenSize().getHeight()); 
		
		gui_interface =gui;
		frame= gui_interface.get_frame();
		frame.setBounds(xSize/4, ySize/9, xSize/2, ySize*6/7);
		frame.setTitle("INSERIMENTO NUMERO GIOCATORI");
		frame.setResizable(false);
		
		top_panel=new JPanel(){ 
			
			@Override
			protected void paintComponent(Graphics graphics) {
				super.paintComponent(graphics);
				
				ImageIcon legni = new ImageIcon(gui_interface.resizeImage("images/images.jpg", 726, 166));
				Image legno = legni.getImage();
				graphics.drawImage(legno, 0, 0, null);
			}
		};
		top_panel.setBounds(0, 0, xSize/2, ySize/7);
		
		main_panel = new JPanel();
		main_panel.setBounds(0, ySize/7,xSize/2,ySize*4/7);
		
		bottom_panel = new JPanel(){ 
			
			@Override
			protected void paintComponent(Graphics graphics) {
				super.paintComponent(graphics);
				
				ImageIcon legni = new ImageIcon(gui_interface.resizeImage("images/images.jpg", 726, 166));
				Image legno = legni.getImage();
				graphics.drawImage(legno, 0, 0, null);
			}
		};
		bottom_panel.setBounds(0, ySize*5/7, xSize/2, ySize/7);
		
		frame.getContentPane().add(top_panel);
		frame.getContentPane().add(bottom_panel);
		frame.getContentPane().add(main_panel);
		
		
	}
	
	/**
	 * Metodo che riceve percorso file  dimensioni e tipo immagine output (esempio BufferedImage.TYPE_INT_RGB o BufferedImage.TYPE_INT_ARGB)
	 * restituisce una buffered Image ridimensionata che puo essere usata per creare ImageIcon
	 * @param nome_immagine
	 * @param width
	 * @param height
	 * @param type
	 * @return
	 */
	private BufferedImage resizeImage(String nome_immagine,int width,int height) {  
	     try {
			
	    	File fileIcon = new File(nome_immagine);
			BufferedImage originalImage = ImageIO.read(fileIcon);
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB );  
	        Graphics2D g = resizedImage.createGraphics();  
	        g.drawImage(originalImage, 0, 0, width, height, null);  
	        g.dispose();  
	        return resizedImage;  
	     } catch (Exception e) {
				e.printStackTrace();
			}  
	     return null;
    }  
	
	public void show() {
		JLabel title= new JLabel("INSERIMENTO NUMERO GIOCATORI");
		title.setForeground(Color.white);
		title.setFont(new Font("Monotype Corsiva", Font.ITALIC, 30));
		
		
		JButton remove_player = new JButton("REMOVE");
		JButton add_player = new JButton("ADD");
		JButton confirm = new JButton("CONFIRM");
		
		num_giocatori_temp = 2;

		final HashMap<Integer, String> map_numplayer_image = new HashMap<Integer, String>();
		
		map_numplayer_image.put(2, "images/num_player/num_horse2.png");
		map_numplayer_image.put(3, "images/num_player/num_horse3.png");
		map_numplayer_image.put(4, "images/num_player/num_horse4.png");
		map_numplayer_image.put(5, "images/num_player/num_horse5.png");
		map_numplayer_image.put(6, "images/num_player/num_horse6.png");

		
		
		ImageIcon imgplayer = new ImageIcon(resizeImage("images/num_player/num_horse2.png", xSize, ySize*5/7));
		image_num_player = new JLabel();
		image_num_player.setIcon(imgplayer);
		
		
		
		add_player.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (num_giocatori_temp<=5) {
					num_giocatori_temp++;
					image_num_player.setIcon(new ImageIcon(resizeImage(map_numplayer_image.get(num_giocatori_temp),xSize,ySize*5/7)));
					
				}
				else {					
					
					BufferedImage imgerror6 = resizeImage("images/num_player/num_giocatori_6.png", 150, 150);
					JOptionPane.showMessageDialog(null, "Non si possono inserire piu di 6 giocatori","Errore",JOptionPane.INFORMATION_MESSAGE,
	                        new ImageIcon(imgerror6));
				}
			}
		});
		remove_player.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (num_giocatori_temp>=3) {
					num_giocatori_temp--;
					image_num_player.setIcon(new ImageIcon(resizeImage(map_numplayer_image.get(num_giocatori_temp),xSize,ySize*5/7)));

				}
				else {
					BufferedImage imgerror2 = resizeImage("images/num_player/num_giocatori_2.png", 150, 150);
					JOptionPane.showMessageDialog(null,"Ci devono essere almeno 2 giocatori","Errore",JOptionPane.INFORMATION_MESSAGE,
	                       new ImageIcon(imgerror2));
				}			
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				setNumPlayerEvent event = new setNumPlayerEvent(num_giocatori_temp);
				event1.getController().how_many_player(event);
		}
			});

		top_panel.add(title);		
		main_panel.add(image_num_player);
		bottom_panel.add(remove_player);
		bottom_panel.add(add_player);
		bottom_panel.add(confirm);
		
		frame.setVisible(true);

	}
	public static void main(String[] args) {
		Giocatore_GUI giocatore_GUI= new Giocatore_GUI(new GUI_interface(), null);
		giocatore_GUI.show();
	}
	}

