package membership;

public class GoldMembership extends Membership {

	public GoldMembership() {
		super("Gold", 0.80, 3000.0);
	}
	public GoldMembership(Membership previousMembership) {
		super("Gold", 0.80, 3000.0, previousMembership);
	}
}
