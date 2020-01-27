package dk.jarry.echo.boundary;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*")
public class Echo extends HttpServlet {

	private static final long serialVersionUID = -4142841682898464412L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = getTextResponse(req);
		resp.getWriter().print(sb.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = getTextResponse(req);
		resp.getWriter().print(sb.toString());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = getTextResponse(req);
		resp.getWriter().print(sb.toString());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = getTextResponse(req);
		resp.getWriter().print(sb.toString());
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = getTextResponse(req);
		resp.getWriter().print(sb.toString());
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = getTextResponse(req);
		resp.getWriter().print(sb.toString());
	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(req, resp);
	}

	StringBuilder getTextResponse(HttpServletRequest req) {

		StringBuilder sb = new StringBuilder();

		sb.append("HEADERS\n");
		sb.append(getTextHeader(req));
		sb.append("----------------------------------------------------------------------------\n");
		sb.append("\n");

		sb.append("PARAMS\n");
		sb.append(getTextParams(req));
		sb.append("----------------------------------------------------------------------------\n");
		sb.append("\n");
		
		try {
			sb.append("PAYLOAD\n");
			sb.append(getTextPayload(req));
			sb.append("\n----------------------------------------------------------------------------\n");
			sb.append("\n");
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		sb.append("EXTRA\n");
		sb.append(getTextExtra(req));
		sb.append("----------------------------------------------------------------------------\n");
		sb.append("\n");

		return sb;
	}

	StringBuilder getTextHeader(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();

		Enumeration<String> headerNames = req.getHeaderNames();

		headerNames.asIterator().forEachRemaining(
				name -> sb.append(name + ":" + req.getHeader(name) + "\n"));
		return sb;
	}

	StringBuilder getTextParams(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();

		Enumeration<String> paramasNames = req.getParameterNames();

		paramasNames.asIterator().forEachRemaining(
				name -> sb.append(name + ":" + req.getParameter(name) + "\n"));
		return sb;
	}

	StringBuilder getTextPayload(HttpServletRequest req) throws IOException {

		ServletInputStream servletInputStream = req.getInputStream();

		final int bufferSize = 1024;
		final char[] buffer = new char[bufferSize];
		final StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(servletInputStream, "UTF-8");
		for (;;) {
			int rsz = in.read(buffer, 0, buffer.length);
			if (rsz < 0)
				break;
			out.append(buffer, 0, rsz);
		}

		return out;
	}
	
	StringBuilder getTextExtra(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		String remoteHost = req.getRemoteHost();
		String remoteUser = req.getRemoteUser();
		String requestURI = req.getRequestURI();
		String method = req.getMethod();

		sb.append("remoteHost").append(":").append(remoteHost).append("\n");
		sb.append("remoteUser").append(":").append(remoteUser).append("\n");
		sb.append("requestURI").append(":").append(requestURI).append("\n");
		sb.append("method").append(":").append(method).append("\n");

		return sb;
	}

}
