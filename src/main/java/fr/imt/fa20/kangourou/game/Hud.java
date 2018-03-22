package fr.imt.fa20.kangourou.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.*;

public class Hud {

	private Image playerbars;
	private static final int P_BAR_X = 100;
	private static final int P_BAR_Y = 500;

	public void init() throws SlickException {
		this.playerbars = new Image("hud.png");
	}
	
	public void render(Graphics g) {
		g.resetTransform();
		g.drawImage(this.playerbars, P_BAR_X, P_BAR_Y);
		g.drawString("AAAAAAAAAA ", 110, 515);
		g.drawString("test", 110, 530);
		g.drawString("test", 110, 545);
		g.drawString("test", 110, 560);

	}
	
	public int dialogue (Graphics g, String str) {
		//return -1 si le string a été affiché correctement
		//sinon return l'index du derniere charactere affiché
		g.drawImage(this.playerbars, P_BAR_X, P_BAR_Y);
		int indexDuDernierEspace = 0;
		int numeroDeLignes = 0;
		int debutDerniereLigne = 0;
		boolean verif = true;
		int i;
		if(str.length() > 60){
			char mesCharacteres[] = str.toCharArray();
			while(numeroDeLignes <3 && verif){
				for(i = 0 + debutDerniereLigne; i<60+debutDerniereLigne; i++){
					if(str.length() == i - 1){
						verif = false;
					}else{
						if(mesCharacteres[i] == ' '){
							indexDuDernierEspace = i + debutDerniereLigne;
						}
					}
					g.drawString(Arrays.toString(mesCharacteres), 110, 515 + 15*numeroDeLignes);
				}
				debutDerniereLigne = indexDuDernierEspace;
				numeroDeLignes++;
			}
			if(verif){
				return debutDerniereLigne;
			}else{
				return -1;
			}
			
		}else{
			g.drawString(str, 110, 515);
			return -1;
		}
		
	}
}