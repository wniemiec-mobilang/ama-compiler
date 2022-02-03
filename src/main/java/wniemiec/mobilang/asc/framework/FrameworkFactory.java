package wniemiec.mobilang.asc.framework;

import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.framework.parser.FrameworkParserFactory;


/**
 * Provides factories of a framework.
 */
public interface FrameworkFactory {

    /**
     * Provides parser factory of a framework.
     * 
     * @return      Parser factory
     */
    FrameworkParserFactory getParserFactory();

    /**
     * Provides coder factory of a framework.
     * 
     * @return      Coder factory
     */
    FrameworkCoderFactory getCoderFactory();

    /**
     * Provides project manager factory of a framework.
     * 
     * @return      Project manager factory
     */
    FrameworkProjectManagerFactory getProjectManagerFactory();
}
