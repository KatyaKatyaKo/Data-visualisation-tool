package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class opener {
JFrame opener = new JFrame();
	
	public opener() {
			final int second = 1000;
			opener.setSize(200, 200);
			opener.setLocationRelativeTo(null);
			opener.setUndecorated(true); //permet de ne pas avoir les boutons et l'entête
			
			JLabel image = new JLabel(new ImageIcon("/Users/Hekatari/eclipse-workspace/NFA032_Project/src/GUI/CSVtoSVG.png"));
			
			opener.add(image);
			opener.setVisible(true);
			
			/*
			Timer timer =new Timer(second, this);
			timer.start();
		
			/*
			 timer = new Timer(second, new ActionListener() {
			    public void actionPerformed(ActionEvent evt) {
			    	if(evt.getSource() == timer)
			            opener.setVisible(false);    
			        }
			).start();
			*/
	/*
	
	timer = new Timer(second, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	if(evt.getSource() == timer)
	            opener.setVisible(false);    
	        
	).start();
	
	
			@Override
			public void actionPerformed(ActionEvent evt) {
				Object timer = null;
				if(evt.getSource() == timer)
		            opener.setVisible(false); 		
		*/            	            
	}	
}