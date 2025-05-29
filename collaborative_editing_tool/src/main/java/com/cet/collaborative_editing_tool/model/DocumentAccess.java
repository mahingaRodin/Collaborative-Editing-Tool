package com.cet.collaborative_editing_tool.model;

import com.cet.collaborative_editing_tool.enums.ERoles;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "document_access")
public class DocumentAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERoles accessLevel;

    @Column(nullable = false)
    private OffsetDateTime grantedAt = OffsetDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ERoles getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(ERoles accessLevel) {
        this.accessLevel = accessLevel;
    }

    public OffsetDateTime getGrantedAt() {
        return grantedAt;
    }

    public void setGrantedAt(OffsetDateTime grantedAt) {
        this.grantedAt = grantedAt;
    }
}
