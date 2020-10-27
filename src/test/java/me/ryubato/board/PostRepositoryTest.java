package me.ryubato.board;

import me.ryubato.board.domain.Board;
import me.ryubato.board.domain.Post;
import me.ryubato.board.domain.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private PostRepository postRepository;

    @Test
    void findByBoardId() {
        //given
        Board board = Board.builder().name("자유게시판").build();
        em.persist(board);

        Post post = Post.builder()
                .boardId(board.getId())
                .title("테스트")
                .writer("관리자")
                .content("없음").build();
        em.persist(post);

        em.flush();
        em.clear();

        //when
        List<Post> posts = postRepository.findByBoardId(board.getId());

        //then
        Assertions.assertThat(posts.size()).isEqualTo(1);
    }
}