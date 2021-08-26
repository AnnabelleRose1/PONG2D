public class AIController {
    public PlayerController playerController;
    public Rect ballRect;

    public AIController(PlayerController plrController, Rect ball) {
        this.playerController = plrController;
        this.ballRect = ball;
    }
    public void update(double dt) {
        playerController.update(dt);
        // Implementing the ai movements
        if (ballRect.y < playerController.rect.y) {
            playerController.moveUp(dt);
        } else if (ballRect.y + ballRect.height > playerController.rect.y + playerController.rect.height) {
            playerController.moveDown(dt);
        }
    }
}
