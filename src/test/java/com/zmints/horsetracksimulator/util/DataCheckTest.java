package com.zmints.horsetracksimulator;

import com.zmints.horsetracksimulator.util.DataCheck;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class DataCheckTest {

    @Test
    void checkNumber()   {
        Assert.assertTrue(DataCheck.isNumeric("10"));
    }

    @Test
    void checkAlphabet()   {
        Assert.assertFalse(DataCheck.isNumeric("A"));
    }

    @Test
    void checkAlphaNumeric() {
        Assert.assertFalse(DataCheck.isNumeric("A10"));
    }

}