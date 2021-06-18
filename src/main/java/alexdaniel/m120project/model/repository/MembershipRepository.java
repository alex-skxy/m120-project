package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Membership;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembershipRepository {
    private static List<Membership> memberships;

    static {
        memberships = new ArrayList<>();
        createMembership(Membership.builder().title("Gold").extraDays(15).build());
        createMembership(Membership.builder().title("Silver").extraDays(10).build());
        createMembership(Membership.builder().title("Bronze").extraDays(5).build());
        createMembership(Membership.builder().title("Default").extraDays(0).build());
        createMembership(Membership.builder().title("Piss").extraDays(-5).build());

    }

    public static Membership getMembership(String title) {
        return memberships.stream().filter(membership -> Objects.equals(membership.getTitle(), title)).findFirst().orElse(memberships.get(3));
    }

    public static List<Membership> getAll() {
        return ImmutableList.copyOf(memberships);
    }

    private static void createMembership(Membership membership) {
        memberships.add(membership);
        membership.setId((long) memberships.indexOf(membership));
    }
}
