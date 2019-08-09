package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Repository.ArticleRepository;

/**
 * 機能とViewを操作するコントローラクラスです.
 * 
 * @author yukihiro.yokogawa
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	
	/**
	 * 記事,コメントを検索する機能を呼び出し、bbsページに遷移させるメソッドです.
	 * 
	 * @param model リクエストスコープ
	 * @return 掲示板
	 */
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("bbsList",articleRepository.findAll());
		return "bbs";
	}

}
