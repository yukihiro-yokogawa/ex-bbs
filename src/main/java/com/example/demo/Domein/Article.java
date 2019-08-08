package com.example.demo.Domein;

import java.util.List;

/**
 * 記事に関するドメインを受け取るクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
public class Article {

	/** 記事ID */
	Integer id;
	/** 記事投稿者名 */
	String name;
	/** 記事 */
	String content;
	/** コメント一覧 */
	List<Comment> commentList;

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
