package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Membership;
import alexdaniel.m120project.model.entity.Movie;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembershipRepository {
    private static List<Membership> memberships;

    static {
        memberships = new ArrayList<>();
        createMembership(new Membership(null, "Gold", 15));
        createMembership(new Membership(null, "Silver", 10));
        createMembership(new Membership(null, "Bronze", 5));
        createMembership(new Membership(null, "Default", 0));
        createMembership(new Membership(null, "Piss", -5));
    }

    public static Membership getMembership(String title) {
        return memberships.stream().filter(membership -> Objects.equals(membership.title, title)).findFirst().orElse(memberships.get(3));
    }

    public static List<Membership> getAll() {
        return ImmutableList.copyOf(memberships);
    }

    private static void createMembership(Membership membership) {
        memberships.add(membership);
        membership.id = (long) memberships.indexOf(membership);
    }
}
