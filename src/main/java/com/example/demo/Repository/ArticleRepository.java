package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.Domein.Article;
import com.example.demo.Domein.Comment;

/**
 * BBSの記事、コメント欄を実際に操作するメソッドです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**	現在検索中の記事IDと前回の記事IDを比較し、場合によってコメントのみ取り出すメソッドです. */
	private static final ResultSetExtractor<List<Article>> ARTICLE_EXTRACTOR = (rs) -> {

		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		
		//記事ID退避用の変数
		int articleIdBefore = -1;
		
		//次の行を検索（初めて検索するときは1行目
		while (rs.next()) {
			
			//現在検索している記事ID
			int articleIdNow = rs.getInt("art_id");
			
			//記事IDを比較し、一致しなければArrayList型のcommentオブジェクトを含んだarticleオブジェクトを新規で作成
			if (articleIdBefore != articleIdNow) {

				Article article = new Article();
				article.setId(rs.getInt("art_id"));
				article.setName(rs.getString("art_name"));
				article.setContent(rs.getString("art_content"));

				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				
				//今回検索した記事IDを変数に退避
				articleIdBefore = articleIdNow;
				articleList.add(article);
			}
			
			//コメント情報を含んだcommentオブジェクトを、前回作ったarticleオブジェクト内のcommentオブジェクトに入れる
			Comment comment = new Comment();
			comment.setId(rs.getInt("cmt_id"));
			comment.setName(rs.getString("cmt_name"));
			comment.setContent(rs.getString("cmt_content"));
			comment.setArticleld(rs.getInt("cmt_article_id"));
			commentList.add(comment);

		}
		return articleList;
	};

//	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
//
//		Article article = new Article();
//		article.setId(rs.getInt("art_id"));
//		article.setName(rs.getString("art_name"));
//		article.setContent(rs.getString("art_content"));
//
//		Comment comment = new Comment();
//		comment.setId(rs.getInt("cmt_id"));
//		comment.setName(rs.getString("cmt_name"));
//		comment.setContent(rs.getString("cmt_content"));
//		comment.setArticleld(rs.getInt("cmt_article_id"));
//
//		List<Comment> commentList = new ArrayList<>();
//		commentList.add(comment);
//
//		article.setCommentList(commentList);
//
//		return article;
//	};

	/**
	 * 記事とコメントを全検索するメソッドです.
	 * 
	 * @return 記事とコメントの情報が全て入ったリスト
	 */
	public List<Article> findAll() {

		String sql = "SELECT art.id AS art_id,art.name AS art_name,art.content AS art_content,cmt.id AS cmt_id,cmt.name AS cmt_name,cmt.content AS cmt_content, cmt.article_id AS cmt_article_id FROM articles art JOIN comments cmt ON art.id = cmt.article_id";

		List<Article> articleList = template.query(sql, ARTICLE_EXTRACTOR);

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
