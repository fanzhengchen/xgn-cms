package com.xgn.cms.generator;

import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public class CmsIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return UUID.randomUUID().toString();
    }
}
