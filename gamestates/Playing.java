package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import entities.Player2;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods {
	private Player player;
	private Player2 player2;
	private LevelManager levelManager;

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player2=new Player2(300, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		player2.loadLvlData(levelManager.getCurrentLevel().getLevelData());
	}

	@Override
	public void update() {
		levelManager.update();
		player.update();
		player2.update();

	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		player2.render(g);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_BACK_SPACE:
			Gamestate.state = Gamestate.MENU;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowFocusLost() {
		player.resetDirBooleans();
		player2.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}
	public Player2 getPlayer2(){
		return player2;
	}

}