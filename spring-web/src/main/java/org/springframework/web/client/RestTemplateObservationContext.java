/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.client;

import io.micrometer.observation.transport.RequestReplySenderContext;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;

/**
 * Context that holds information for metadata collection
 * during the {@link org.springframework.web.client.RestTemplate} observations.
 * <p>This context also extends {@link RequestReplySenderContext} for propagating tracing
 * information with the HTTP client exchange.
 * @author Brian Clozel
 * @since 6.0
 */
public class RestTemplateObservationContext extends RequestReplySenderContext<ClientHttpRequest, ClientHttpResponse> {

	@Nullable
	private String uriTemplate;

	public RestTemplateObservationContext() {
		super(RestTemplateObservationContext::setRequestHeader);
	}

	private static void setRequestHeader(@Nullable ClientHttpRequest request, String name, String value) {
		if (request != null) {
			request.getHeaders().set(name, value);
		}
	}

	/**
	 * Return the URI template used for the current client exchange, {@code null} if none was used.
	 */
	@Nullable
	public String getUriTemplate() {
		return this.uriTemplate;
	}

	/**
	 * Set the URI template used for the current client exchange.
	 */
	public void setUriTemplate(@Nullable String uriTemplate) {
		this.uriTemplate = uriTemplate;
	}

}
