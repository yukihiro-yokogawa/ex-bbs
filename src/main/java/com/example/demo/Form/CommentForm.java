package com.example.demo.Form;

/**
 * コメント投稿者の情報をドメインから受け取るフォームクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
public class CommentForm {

	/** コメント投稿者名 */
	String name;

	/** コメント内容 */
	String content;

	/** 記事ID */
	String articleId;

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

}
