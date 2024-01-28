package com.sigep.specifications;

import com.sigep.enums.State;
import com.sigep.models.VehicleModel;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecification {

    private VehicleSpecification() {

    }

    public static Specification<VehicleModel> hasState(String state) {
        return ((root, query, cb) -> state == null ? cb.conjunction()
                : cb.equal(root.get("state"), State.valueOf(state)));
    }

    public static Specification<VehicleModel> hasMake(String make) {
        return ((root, query, cb) -> make == null || make.isEmpty() ? cb.conjunction()
                : cb.equal(root.get("make"), make));
    }

    public static Specification<VehicleModel> hasYear(String year) {
        return ((root, query, cb) -> year == null || year.isEmpty() ? cb.conjunction()
                : cb.equal(root.get("year"), Integer.parseInt(year)));
    }

}
