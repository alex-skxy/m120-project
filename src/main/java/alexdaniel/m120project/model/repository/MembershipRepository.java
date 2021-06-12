package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembershipRepository {
    private static List<Membership> memberships;

    static {
        memberships = new ArrayList<>();
        memberships.add(new Membership(null, "Gold", 15));
        memberships.add(new Membership(null, "Silver", 10));
        memberships.add(new Membership(null, "Bronze", 5));
        memberships.add(new Membership(null, "Default", 0));
        memberships.add(new Membership(null, "Piss", -5));
    }

    public static Membership getMembership(String title) {
        return memberships.stream().filter(membership -> Objects.equals(membership.title, title)).findFirst().orElse(memberships.get(3));
    }

    private static void createMembership(Membership membership) {
        memberships.add(membership);
        membership.id = (long) memberships.indexOf(membership);
    }
}
