package transactions;

import java.time.LocalDate;

import product.Product;
import staff.Employee;
import user.Member;

public class MemberSale extends Sales {
	private Member member;

	public MemberSale(Product p, int quantity, LocalDate Date, Employee employee, double markedprice,
			double sellingPrice, String orderRefNo, Member member) {
		super(p, quantity, Date, employee, markedprice, sellingPrice, orderRefNo);
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
