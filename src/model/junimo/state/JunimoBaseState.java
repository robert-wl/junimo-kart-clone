package model.junimo.state;


import model.junimo.Junimo;

public abstract class JunimoBaseState {
	protected Junimo junimo;

	protected JunimoBaseState(Junimo junimo) {
		this.junimo = junimo;
	}

	public abstract void onEnter();

	public abstract void onUpdate();

	public abstract void onExit();
}
