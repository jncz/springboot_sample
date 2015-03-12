package com.ibm.spss.boot.web.sitemesh;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import com.ibm.spss.boot.Constants;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {

	private static final String SITEMESH_DECORATOR_TEMPLATE = "/pages/sitemesh/decorator.html";

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", SITEMESH_DECORATOR_TEMPLATE)
				.addExcludedPath("/jersey/*")
				.addExcludedPath(Constants.RESTPATH_SPRING_SAMPLE+"/*")
				.addExcludedPath("/pages/sitemesh/*");
	}
}
