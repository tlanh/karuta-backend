/* =======================================================
	Copyright 2020 - ePortfolium - Licensed under the
	Educational Community License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may
	obtain a copy of the License at

	http://www.osedu.org/licenses/ECL-2.0

	Unless required by applicable law or agreed to in writing,
	software distributed under the License is distributed on an "AS IS"
	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
	or implied. See the License for the specific language governing
	permissions and limitations under the License.
   ======================================================= */

package eportfolium.com.karuta.webapp.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import eportfolium.com.karuta.business.contract.ResourceManager;
import eportfolium.com.karuta.document.ResourceDocument;
import eportfolium.com.karuta.document.ResourceList;
import eportfolium.com.karuta.model.exception.BusinessException;
import eportfolium.com.karuta.webapp.annotation.InjectLogger;
import eportfolium.com.karuta.webapp.util.UserInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/resources")
public class ResourcesController extends AbstractController {

    @Autowired
    private ResourceManager resourceManager;

    @InjectLogger
    private static Logger logger;

    /**
     * Fetch resource from node uuid.
     *
     * GET /rest/api/resources/resource/{node-parent-id}
     */
    @GetMapping(value = "/resource/{node-parent-id}", consumes = "application/xml",
            produces = {"application/json", "application/xml"})
    public HttpEntity<ResourceDocument> getResource(@RequestParam("group") long groupId,
                                                    @PathVariable("node-parent-id") UUID nodeParentId,
                                                    HttpServletRequest request) throws BusinessException {
        UserInfo ui = checkCredential(request);

        return new HttpEntity<>(resourceManager.getResource(nodeParentId, ui.userId, groupId));
    }

    /**
     * Fetch all resource in a portfolio.
     *
     * GET /rest/api/resources/portfolios/{portfolio-id}
     */
    @GetMapping(value = "/portfolios/{portfolio-id}", produces = {"application/xml"})
    public HttpEntity<ResourceList> getResources(@RequestParam("group") long groupId,
                                                 @PathVariable("portfolio-id") UUID portfolioId,
                                                 HttpServletRequest request) {

        UserInfo ui = checkCredential(request);

        return new HttpEntity<>(resourceManager.getResources(portfolioId, ui.userId, groupId));
    }

    /**
     * Modify resource content.
     *
     * PUT /rest/api/resources/resource/{node-parent-uuid}
     */
    @PutMapping(value = "/resource/{node-parent-uuid}", produces = "application/xml")
    public String putResource(@RequestBody ResourceDocument resource,
                              @RequestParam("group") long groupId,
                              @PathVariable("node-parent-uuid") UUID parentNodeId,
                              HttpServletRequest request) throws BusinessException, JsonProcessingException {

        UserInfo ui = checkCredential(request);

        return resourceManager.changeResource(parentNodeId, resource, ui.userId, groupId)
                    .toString();
    }

    /**
     * Add a resource (?).
     *
     * POST /rest/api/resources/{node-parent-uuid}
     */
    @PostMapping(value = "/{node-parent-uuid}", produces = "application/xml")
    public String postResource(@RequestBody ResourceDocument resource,
                               @RequestParam("group") long groupId,
                               @PathVariable("node-parent-uuid") UUID parentNodeId,
                               HttpServletRequest request) throws BusinessException {

        UserInfo ui = checkCredential(request);

        return resourceManager.addResource(parentNodeId, resource, ui.userId, groupId);
    }

    /**
     * (?) POST /rest/api/resources
     */
    @PostMapping(produces = "application/xml")
    public String postResources(@RequestBody ResourceDocument resource,
                               @RequestParam("group") long groupId,
                               @RequestParam("resource") UUID resourceId,
                               HttpServletRequest request) throws BusinessException {
        UserInfo ui = checkCredential(request);

        return resourceManager.addResource(resourceId, resource, ui.userId, groupId);
    }

    /**
     * Delete a resource
     *
     * DELETE /rest/api/resources/{resource-id}
     */
    @DeleteMapping(value = "/{resource-id}", produces = "application/xml")
    public String deleteResource(@RequestParam("group") long groupId,
                                 @PathVariable("resource-id") UUID resourceId,
                                 HttpServletRequest request) throws BusinessException {

        UserInfo ui = checkCredential(request);

        resourceManager.removeResource(resourceId, ui.userId, groupId);

        return "";
    }

}
