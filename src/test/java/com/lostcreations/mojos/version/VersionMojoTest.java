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

import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The test class for VersionMojo.
 * 
 * @author akutz
 * 
 */
public class VersionMojoTest
{
    private VersionMojo mojo;

    @BeforeTest
    public void beforeTest()
    {
        this.mojo = new VersionMojo();
        this.mojo.project = new MavenProject();

        this.mojo.versionPrefixVarName = "version.prefix";
        this.mojo.versionMajorVarName = "version.major";
        this.mojo.versionMinorVarName = "version.minor";
        this.mojo.versionBuildVarName = "version.build";
        this.mojo.versionRevisionVarName = "version.revision";
        this.mojo.versionSuffixVarName = "version.suffix";
        this.mojo.versionStringVarName = "version.string";
        this.mojo.versionString4VarName = "version.string.4";
        this.mojo.versionStringRaw4VarName = "version.string.raw.4";
    }

    @Test
    public void executeTestOne() throws Exception
    {
        this.mojo.project.setVersion("1.0-SNAPSHOT");

        this.mojo.execute();

        Assert.assertEquals(
            "1",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionMajorVarName));

        Assert.assertEquals(
            "0",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionMinorVarName));

        Assert.assertEquals("-SNAPSHOT", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionSuffixVarName));

        Assert.assertEquals("1.0", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionStringVarName));

        Assert.assertEquals("1.0.0.0", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionString4VarName));

        Assert.assertEquals("1.0.0.0-SNAPSHOT", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionStringRaw4VarName));
    }

    @Test
    public void executeTestTwo() throws Exception
    {
        this.mojo.project.setVersion("1.0.1-SNAPSHOT");

        this.mojo.execute();

        Assert.assertEquals(
            "1",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionMajorVarName));

        Assert.assertEquals(
            "0",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionMinorVarName));

        Assert.assertEquals(
            "1",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionBuildVarName));

        Assert.assertEquals("-SNAPSHOT", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionSuffixVarName));

        Assert.assertEquals("1.0.1", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionStringVarName));

        Assert.assertEquals("1.0.1.0", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionString4VarName));

        Assert.assertEquals("1.0.1.0-SNAPSHOT", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionStringRaw4VarName));
    }
    
    @Test
    public void executeTestThree() throws Exception
    {
        this.mojo.project.setVersion("a1.0.1-SNAPSHOT");

        this.mojo.execute();
        
        Assert.assertEquals(
            "a",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionPrefixVarName));

        Assert.assertEquals(
            "1",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionMajorVarName));

        Assert.assertEquals(
            "0",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionMinorVarName));

        Assert.assertEquals(
            "1",
            this.mojo.project.getProperties().getProperty(
                this.mojo.versionBuildVarName));

        Assert.assertEquals("-SNAPSHOT", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionSuffixVarName));

        Assert.assertEquals("1.0.1", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionStringVarName));

        Assert.assertEquals("1.0.1.0", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionString4VarName));

        Assert.assertEquals("a1.0.1.0-SNAPSHOT", this.mojo.project
            .getProperties()
            .getProperty(this.mojo.versionStringRaw4VarName));
    }
}
