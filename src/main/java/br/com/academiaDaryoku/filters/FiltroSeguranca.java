package br.com.academiaDaryoku.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academiaDaryoku.model.TbUsuario;

@WebFilter("/sistema/*")
public class FiltroSeguranca implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		TbUsuario usuarioEntity = null;

		HttpSession sessao = ((HttpServletRequest) request).getSession(false);

		if (sessao != null) {
			usuarioEntity = (TbUsuario) sessao.getAttribute("usuarioLogado");
		}
		if (usuarioEntity == null) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/login/login.xhtml");
		} else {
			String novaPaginaAtual = ((HttpServletRequest) request).getServletPath();
			if (sessao.getAttribute("paginaAtual") == null) {
				sessao.setAttribute("paginaAnterior", novaPaginaAtual);
				sessao.setAttribute("paginaAtual", novaPaginaAtual);
			} else {
				String antigaPaginaAtual = sessao.getAttribute("paginaAtual").toString();
				if (!antigaPaginaAtual.equals(novaPaginaAtual)) {
					sessao.setAttribute("paginaAnterior", antigaPaginaAtual);
					sessao.setAttribute("paginaAtual", novaPaginaAtual);
				}
			}

			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}