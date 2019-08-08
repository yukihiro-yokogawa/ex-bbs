package com.example.demo.Form;

/**
 * 記事投稿者の情報をドメインから受け取るフォームクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
public class ArticleForm {

	/** 記事投稿者名 */
	String name;
	/** 記事内容 */
	String content;

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
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

}
