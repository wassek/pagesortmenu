package org.jahia.modules.pagesortmenu.servlet;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jahia.api.Constants;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.content.JCRSessionWrapper;
import org.osgi.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortPageServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(SortPageServlet.class);
	private static final long serialVersionUID = 1L;

	private HttpService httpService;

	public SortPageServlet() {
	}

	public void postConstruct() {
	}

	public void preDestroy() {
	}

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			JCRSessionWrapper session = JCRSessionFactory.getInstance().getCurrentUserSession();
			if (Constants.EDIT_WORKSPACE.equals(session.getWorkspace().getName())) {
				String path = req.getParameter("path");
				boolean up = req.getParameter("up") != null;
				
				if (path != null) {
					JCRNodeWrapper node = session.getNode(path);
					JCRNodeWrapper rootNode = node.getParent();
					String nodeName = path.substring(path.lastIndexOf("/")+1);
					JCRNodeWrapper upNode = null;
					int nextNode = 0;
					for (JCRNodeWrapper subNode : rootNode.getNodes()) {
						if (!subNode.isNodeType("jnt:page") && !subNode.isNodeType("jnt:navMenuText") && 
								!subNode.isNodeType("jnt:nodeLink") && !subNode.isNodeType("jnt:externalLink")) {
							continue;
						}
						
						if (!up) {
							if (nextNode == 1) {
							  nextNode ++; 
							} else if (nextNode == 2) {
								rootNode.orderBefore(nodeName, subNode.getName());
					            rootNode.saveSession();
					            nextNode = 0;
					            break;
							}
						}
						if (subNode.getName().equals(nodeName)) {
							if (up && upNode != null) {
								rootNode.orderBefore(nodeName, upNode.getName());
					            rootNode.saveSession();
					            break;
							} else if (!up) {
								nextNode = 1;
							}
						}
						upNode = subNode;
						
					}
					if (!up && nextNode > 0) {
						rootNode.orderBefore(nodeName, null);
			            rootNode.saveSession();
					}
					
					
				}

			}

		} catch (RepositoryException ex) {

			logger.error("Sort of Page failed!", ex);
		}
	}

}
