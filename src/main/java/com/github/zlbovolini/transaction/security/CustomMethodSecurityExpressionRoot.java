package com.github.zlbovolini.transaction.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Objects;
import java.util.UUID;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    private final EntityManager entityManager;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, EntityManager entityManager) {
        super(authentication);
        this.entityManager = entityManager;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    void setThis(Object target) {
        this.target = target;
    }

    public boolean isCardOwner(UUID uuid) {
        if (Objects.isNull(authentication)) {
            return false;
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        if (Objects.isNull(jwt)) {
            return false;
        }

        String email = jwt.getClaimAsString("email");
        if (Objects.isNull(email) || email.isBlank()) {
            return false;
        }

        String query = "SELECT count(email) = 1 FROM Card WHERE uuid = :uuid AND email = :email";
        TypedQuery<Boolean> typedQuery = entityManager.createQuery(query, Boolean.class);
        typedQuery.setParameter("uuid", uuid);
        typedQuery.setParameter("email", email);

        return typedQuery.getSingleResult();
    }
}
