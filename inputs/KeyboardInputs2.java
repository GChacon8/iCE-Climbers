package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs2 implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs2(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// case KeyEvent.VK_W:
			// gamePanel.getGame().getPlayer().setUp(false);
			// break;
			case KeyEvent.VK_LEFT:
				gamePanel.getGame().getPlayer2().setLeft(false);
				break;
			// case KeyEvent.VK_S:
			// gamePanel.getGame().getPlayer().setDown(false);
			// break;
			case KeyEvent.VK_RIGHT:
				gamePanel.getGame().getPlayer2().setRight(false);
				break;
			case KeyEvent.VK_UP:
				gamePanel.getGame().getPlayer2().setJump(false);
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			// case KeyEvent.VK_W:
			// gamePanel.getGame().getPlayer().setUp(true);
			// break;
			case KeyEvent.VK_LEFT:
				gamePanel.getGame().getPlayer2().setLeft(true);
				break;
			// case KeyEvent.VK_S:
			// gamePanel.getGame().getPlayer().setDown(true);
			// break;
			case KeyEvent.VK_RIGHT:
				gamePanel.getGame().getPlayer2().setRight(true);
				break;
			case KeyEvent.VK_UP:
				gamePanel.getGame().getPlayer2().setJump(true);
				break;
		}
	}
}