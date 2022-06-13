package jpabook.jpashop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JpashopApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		//최신 버전에서는 JPA쿼리 팩토리 사용을 권장
		JPAQueryFactory query = new JPAQueryFactory(em);

		/* Q파일 생성하는 방법 1*/
		//생성자의 파리미터로 별칭(alias)를 넣는다
		//QHello qHello = new QHello("h");


		/* Q파일 생성하는 방법 2*/
		QHello qHello = QHello.hello;
		Hello result = query.selectFrom(qHello).fetchOne();

		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());

	}

}
