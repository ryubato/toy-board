package me.ryubato.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select c from Comment c where c.parent = ?1")
    List<Comment> findAllByParent(Comment parent);

    @Query(value = "select * from comments c where c.parent_comment_id = ?1", nativeQuery = true)
    List<Comment> findAllByParentUsingNativeQuery(Long id);

}
