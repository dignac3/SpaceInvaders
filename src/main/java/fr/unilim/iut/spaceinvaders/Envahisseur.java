package fr.unilim.iut.spaceinvaders;

public class Envahisseur extends Sprite {

	Direction direction;
	
	public Envahisseur(Position origine, Dimension dimension, int vitesse) {
		super(origine, dimension, vitesse);
		this.direction = Direction.DROITE;
	}
	
	public Envahisseur(Position origine, Dimension dimension, int vitesse,Direction direction) {
		super(origine, dimension, vitesse);
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
}
