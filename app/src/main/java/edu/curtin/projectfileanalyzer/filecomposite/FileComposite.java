package edu.curtin.projectfileanalyzer.filecomposite;

/**
 * Interface provides a contract for all File based composite types
 *
 * @author Kyer Potts
 */
public interface FileComposite {

  /**
   * @return returns the name of the FileComposite. This should always be an
   *         unqualified path
   */
  public String getName();
}
