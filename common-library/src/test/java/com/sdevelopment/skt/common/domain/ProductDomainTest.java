package com.sdevelopment.skt.common.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProductDomainTest {

    @Test
    public void checkIfEqualsReflexive() {
        Product p = new Product("Reflexive");

        Assert.assertEquals(p,p);
    }

    @Test
    public void checkIfEqualsSymmetric() {
        Product p1 = new Product("Symmetric");
        Product p2 = new Product("Symmetric");

        Assert.assertEquals(p1,p2);
        Assert.assertEquals(p2,p1);
    }

    @Test
    public void checkIfEqualsTransitive() {
        Product p1 = new Product("Transitive");
        Product p2 = new Product("Transitive");
        Product p3 = new Product("Transitive");

        Assert.assertEquals(p1,p2);
        Assert.assertEquals(p2,p3);
        Assert.assertEquals(p1,p3);
    }

    @Test
    public void checkIfEqualsConsistent() {
        Product p1 = new Product("Consistent1");
        Product p2 = new Product("Consistent1");
        Product p3 = new Product("Consistent2");

        for(int i = 0; i < 10000; i ++) {
            Assert.assertEquals(p1,p2);
            Assert.assertNotEquals(p2,p3);
            Assert.assertNotEquals(p1,p3);
        }
    }
}
