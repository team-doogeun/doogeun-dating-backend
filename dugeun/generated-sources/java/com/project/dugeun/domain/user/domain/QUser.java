package com.project.dugeun.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 519544760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath basicFilePath = createString("basicFilePath");

    public final StringPath confirmPassword = createString("confirmPassword");

    public final com.project.dugeun.domain.user.domain.profile.QDetailProfile detailProfile;

    public final StringPath email = createString("email");

    public final StringPath externalId = createString("externalId");


    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.project.dugeun.domain.user.domain.profile.QIdealTypeProfile idealTypeProfile;

    public final ListPath<com.project.dugeun.domain.blindDate.domain.Like, com.project.dugeun.domain.blindDate.domain.QLike> likeList = this.<com.project.dugeun.domain.blindDate.domain.Like, com.project.dugeun.domain.blindDate.domain.QLike>createList("likeList", com.project.dugeun.domain.blindDate.domain.Like.class, com.project.dugeun.domain.blindDate.domain.QLike.class, PathInits.DIRECT2);

    public final com.project.dugeun.domain.blindDate.domain.QMatch match;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath secondFilePath = createString("secondFilePath");

    public final StringPath studentId = createString("studentId");

    public final StringPath thirdFilePath = createString("thirdFilePath");

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.detailProfile = inits.isInitialized("detailProfile") ? new com.project.dugeun.domain.user.domain.profile.QDetailProfile(forProperty("detailProfile")) : null;
        this.idealTypeProfile = inits.isInitialized("idealTypeProfile") ? new com.project.dugeun.domain.user.domain.profile.QIdealTypeProfile(forProperty("idealTypeProfile")) : null;
        this.match = inits.isInitialized("match") ? new com.project.dugeun.domain.blindDate.domain.QMatch(forProperty("match")) : null;
    }

}

