package membership;

public class NonMembership extends Membership {

	public NonMembership() {
		super(1.0, 0, "Non-member");
	}

	@Override
	public Membership upgradeMembership() {
		return this;
	}

}
