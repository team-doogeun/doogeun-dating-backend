package com.project.dugeun.domain.blindDate.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatch is a Querydsl query type for Match
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatch extends EntityPathBase<Match> {

    private static final long serialVersionUID = 194964844L;

    public static final QMatch match = new QMatch("match");

    public final DateTimePath<java.time.LocalDateTime> created = createDateTime("created", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.dugeun.domain.user.domain.User, com.project.dugeun.domain.user.domain.QUser> userList = this.<com.project.dugeun.domain.user.domain.User, com.project.dugeun.domain.user.domain.QUser>createList("userList", com.project.dugeun.domain.user.domain.User.class, com.project.dugeun.domain.user.domain.QUser.class, PathInits.DIRECT2);

    public QMatch(String variable) {
        super(Match.class, forVariable(variable));
    }

    public QMatch(Path<? extends Match> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMatch(PathMetadata metadata) {
        super(Match.class, metadata);
    }

}

