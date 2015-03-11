package com.ibm.spss.boot.web.sitemesh;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/", "/pages/sitemesh/decorator.template").addDecoratorPath("/*", "/pages/sitemesh/decorator.template")
				.addExcludedPath("/jersey/*");
	}
}
