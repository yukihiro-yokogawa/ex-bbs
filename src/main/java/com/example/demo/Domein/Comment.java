package com.example.demo.Domein;

/**
 * コメントに関するドメインを受け取るクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
public class Comment {

	/** コメントID */
	Integer id;
	/** コメント投稿者名 */
	String name;
	/** コメント内容 */
	String content;
	/** 記事ID */
	Integer articleld;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleld=" + articleld + "]";
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

	public Integer getArticleld() {
		return articleld;
	}

	public void setArticleld(Integer articleld) {
		this.articleld = articleld;
	}

}
