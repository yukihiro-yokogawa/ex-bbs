package com.example.demo.Domein;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
