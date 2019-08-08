package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.Domein.Article;
import com.example.demo.Domein.Comment;

@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {

		Article article = new Article();
		article.setId(rs.getInt("art.id"));
		article.setName(rs.getString("art.name"));
		article.setContent(rs.getString("art.content"));

		Comment comment = new Comment();
		comment.setId(rs.getInt("cmt.id"));
		comment.setName(rs.getString("cmt.name"));
		comment.setContent(rs.getString("cmt.content"));
		comment.setArticleld(rs.getInt("cmt.article_id"));

		List<Comment> commentList = new ArrayList<>();
		commentList.add(comment);

		article.setCommentList(commentList);

		return article;
	};

	/**
	 * 記事とコメントを全検索するメソッドです.
	 * 
	 * @return 記事とコメントの情報が全て入ったリスト
	 */
	public List<Article> findAll() {

		String sql = "SELECT art.id,art.name,art.content,cmt.id,cmt.name,cmt.content,cmt.article_id FROM articles art JOIN comments cmt ON art.id = cmt.article_id";

		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

		return articleList;
	}

	/**
	 * 記事、又はコメントを挿入するメソッドです。
	 * 
	 * @param article
	 * @param comment
	 */
	public void insert(Article article, Comment comment) {

		SqlParameterSource paramArt = new BeanPropertySqlParameterSource(article);
		SqlParameterSource paramCmt = new BeanPropertySqlParameterSource(comment);

		if (article.getId() == null) {
			String sqlArt = "INSERT INTO articles (name,content) VALUES (:name,:content)";

			template.update(sqlArt, paramArt);

		} else if (comment.getId() == null) {
			String sqlCmt = "INSERT INTO comments (name,content,article_id) VALUES (:name,:content,article_id)";
			
			template.update(sqlCmt, paramCmt);
		}

	}

}
