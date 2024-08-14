package model.collideable;

public class CollisionBox {
	public double x1;
	public double y1;
	public double x2;
	public double y2;

	public CollisionBox(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public final void updateCollider(double x1, double y1, double x2, double y2) {
//		CanvasHelper.drawRectangle(x1, y1, x2 - x1, y2 - y1);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public final CollisionBox getNextCollider(double speedX, double speedY) {
		return new CollisionBox(this.x1 + speedX,
		                        this.y1 + speedY,
		                        this.x2 + speedX,
		                        this.y2 + speedY);
	}

	public final boolean isColliding(CollisionBox other) {
		if (other == null) {
			return false;
		}
		return this.x1 < other.x2 && this.x2 > other.x1 && this.y1 < other.y2 && this.y2 > other.y1;
	}
}
