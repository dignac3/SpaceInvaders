package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvadersTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n", spaceinvaders.toString());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + ".......V.......\n", spaceinvaders.toString());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(15, 9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(-1, 9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, 10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, -1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}

	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".......VVV.....\n" + ".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(10, 2), new Position(7, 9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 4), new Position(7, 1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

	}

	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "..........VVV..\n" + "..........VVV..\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(12, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "............VVV\n" + "............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(10, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "............VVV\n" + "............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "....VVV........\n" + "....VVV........\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(0, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "VVV............\n" + "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(1, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "VVV............\n" + "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + ".......MMM.....\n" + ".......MMM.....\n"
				+ ".....VVVVVVV...\n" + ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(7, 9), 1);
	}
	
	@Test
    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
	   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

	   spaceinvaders.deplacerMissile();
	   
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       ".......MMM.....\n" + 
       ".......MMM.....\n" + 
       "...............\n" + 
       "...............\n" + 
       ".....VVVVVVV...\n" + 
       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
	
	@Test
	   public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
		   for (int i = 1; i <=6 ; i++) {
			   spaceinvaders.deplacerMissile();
		   }
		   
		   spaceinvaders.deplacerMissile();
		   
	       assertEquals("" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
	@Test
	   public void test_unEnvahisseurEstCorrectementPositionneDansEspaceDeJeu() {

		   spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(1,0), 1,Direction.DROITE);

		   
	       assertEquals("" +
	       ".E.............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
	@Test
	public void test_UnNouvelEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(15, 9), 1,Direction.DROITE);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(-1, 9), 1,Direction.DROITE);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, 10), 1,Direction.DROITE);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1), new Position(14, -1), 1,Direction.DROITE);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}

	@Test
	public void test_unNouvelEnvahisseurAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3, 2), new Position(1, 1), 1,Direction.DROITE);
		assertEquals("" +
			       ".EEE...........\n" + 
			       ".EEE...........\n" +
			       "...............\n" + 
			       "...............\n" +
			       "...............\n" +
			       "...............\n" + 
			       "...............\n" +
			       "...............\n" + 
			       "...............\n" + 
			       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouvelEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(10, 2), new Position(7, 9), 1,Direction.DROITE);
			fail("Dépassement de l'envahisseur à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3, 4), new Position(7, 1), 1,Direction.DROITE);
			fail("Dépassement de l'envahisseur vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

	}
	
	@Test
	   public void test_DeplacerEnvahisseur_VersLaDroite() {

		   spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(0,1), 1,Direction.DROITE);
		   spaceinvaders.deplacerEnvahisseurVersLaDroite();
		   
	       assertEquals("" +
	       ".EE............\n" + 
	       ".EE............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
	@Test
	   public void test_DeplacerEnvahisseur_VersLaGauche() {

		   spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(3,1), 1,Direction.DROITE);
		   spaceinvaders.deplacerEnvahisseurVersLaGauche();
		   
	       assertEquals("" +
	       "..EE...........\n" + 
	       "..EE...........\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
	@Test
	   public void test_DeplacerEnvahisseur_CollsionAvecBordDroit() {

		   spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(13,1), 1,Direction.DROITE);
		   spaceinvaders.deplacerEnvahisseur();
		   
	       assertEquals("" +
	       "............EE.\n" + 
	       "............EE.\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
	@Test
	   public void test_DeplacerEnvahisseur_CollsionAvecBordGauche() {

		   spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(0,1), 1,Direction.GAUCHE);
		   spaceinvaders.deplacerEnvahisseur();
		   
	       assertEquals("" +
	       ".EE............\n" + 
	       ".EE............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
}