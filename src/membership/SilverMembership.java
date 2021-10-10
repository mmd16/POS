package membership;

public class SilverMembership extends Membership {
	public SilverMembership(){
		super("Silver", 0.9, 2000.0);
	}
	public SilverMembership(Membership previousMembership){
		super("Silver", 0.9, 2000.0, previousMembership);
	}
}
