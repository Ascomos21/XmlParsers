<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Test Case Results</h1>
                <table border="1">
                    <tr>
                        <th>nameGem</th>
                        <th>origin</th>
                        <th>color</th>
                        <th>countOfFaces</th>
                    </tr>
                    <xsl:for-each select="reserve/*">
                        <tr>
                            <td>
                                <xsl:value-of select="nameGem"/>
                            </td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:value-of select="visualParameters/color"/>
                            </td>
                            <td>
                                <xsl:value-of select="visualParameters/countOfFaces"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>