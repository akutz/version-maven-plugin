/*******************************************************************************
 * Copyright (c) 2012, Schley Andrew Kutz All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * - Neither the name of the Schley Andrew Kutz nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package com.lostcreations.mojos.version;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

/**
 * Parses the version from the project into standard version components.
 * 
 * @goal version
 * @phase initialize
 * @author akutz
 */
public class VersionMojo extends AbstractMojo
{
    /**
     * The Maven project.
     * 
     * @component
     */
    MavenProject project;

    /**
     * Sets the MAJOR version component of the produced version string.
     * 
     * @parameter expression="${version.major}"
     */
    String versionMajor;

    /**
     * Sets the MINOR version component of the produced version string.
     * 
     * @parameter expression="${version.minor}"
     */
    String versionMinor;

    /**
     * Sets the BUILD version component of the produced version string.
     * 
     * @parameter expression="${version.build}"
     */
    String versionBuild;

    /**
     * Sets the REVISION version component of the produced version string.
     * 
     * @parameter expression="${version.revision}"
     */
    String versionRevision;

    /**
     * The name of the variable to hold the MAJOR part of the version.
     * 
     * @parameter expression="${version.major.varname}"
     *            default-value="version.major"
     */
    String versionMajorVarName;

    /**
     * The name of the variable to hold the MINOR part of the version.
     * 
     * @parameter expression="${version.minor.varname}"
     *            default-value="version.minor"
     */
    String versionMinorVarName;

    /**
     * The name of the variable to hold the BUILD part of the version.
     * 
     * @parameter expression="${version.build.varname}"
     *            default-value="version.build"
     */
    String versionBuildVarName;

    /**
     * The name of the variable to hold the REVISION part of the version.
     * 
     * @parameter expression="${version.revision.varname}"
     *            default-value="version.revision"
     */
    String versionRevisionVarName;

    /**
     * The name of the variable to hold the prefix part of the version.
     * 
     * @parameter expression="${version.prefix.varname}"
     *            default-value="version.prefix"
     */
    String versionPrefixVarName;

    /**
     * The name of the variable to hold the suffix part of the version.
     * 
     * @parameter expression="${version.suffix.varname}"
     *            default-value="version.suffix"
     */
    String versionSuffixVarName;

    /**
     * The name of the variable to hold the version string (sans prefix and
     * suffix) as a version with the number of components specified.
     * 
     * @parameter expression=${version.string.varname}"
     *            default-value="version.string"
     */
    String versionStringVarName;

    /**
     * The name of the variable to hold the version string (sans prefix and
     * suffix) as a version with four components.
     * 
     * @parameter expression=${version.string.4.varname}"
     *            default-value="version.string.4"
     */
    String versionString4VarName;

    /**
     * The name of the variable to hold the raw version string as a version with
     * four components.
     * 
     * @parameter expression=${version.string.raw.4.varname}"
     *            default-value="version.string.raw.4"
     */
    String versionStringRaw4VarName;

    /**
     * Execute the Mojo's goal.
     */
    @Override
    public void execute() throws MojoExecutionException
    {
        // Gets the project's version.
        try
        {
            Version v = Version.parse(this.project.getVersion());

            // See if any of the forced version parts are set.
            if (StringUtils.isNotEmpty(this.versionMajor))
            {
                v.setMajor(this.versionMajor);
            }

            if (StringUtils.isNotEmpty(this.versionMinor))
            {
                v.setMinor(this.versionMinor);
            }

            if (StringUtils.isNotEmpty(this.versionBuild))
            {
                v.setBuild(this.versionBuild);
            }

            if (StringUtils.isNotEmpty(this.versionRevision))
            {
                v.setRevision(this.versionRevision);
            }

            if (StringUtils.isNotEmpty(v.getMajor()))
            {
                this.project.getProperties().setProperty(
                    this.versionMajorVarName,
                    v.getMajor());
            }

            if (StringUtils.isNotEmpty(v.getMinor()))
            {
                this.project.getProperties().setProperty(
                    this.versionMinorVarName,
                    v.getMinor());
            }

            if (StringUtils.isNotEmpty(v.getBuild()))
            {
                this.project.getProperties().setProperty(
                    this.versionBuildVarName,
                    v.getBuild());
            }

            if (StringUtils.isNotEmpty(v.getRevision()))
            {
                this.project.getProperties().setProperty(
                    this.versionRevisionVarName,
                    v.getRevision());
            }

            if (StringUtils.isNotEmpty(v.getPrefix()))
            {
                this.project.getProperties().setProperty(
                    this.versionPrefixVarName,
                    v.getPrefix());
            }

            if (StringUtils.isNotEmpty(v.getSuffix()))
            {
                this.project.getProperties().setProperty(
                    this.versionSuffixVarName,
                    v.getSuffix());
            }

            this.project.getProperties().setProperty(
                this.versionStringVarName,
                v.toString());

            this.project.getProperties().setProperty(
                this.versionString4VarName,
                v.toString(4));

            this.project.getProperties().setProperty(
                this.versionStringRaw4VarName,
                v.toStringWithPrefixAndSuffix(4));
        }
        catch (Exception e)
        {
            throw new MojoExecutionException("error parsing the version", e);
        }
    }
}
