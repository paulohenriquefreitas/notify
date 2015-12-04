package br.com.broker;

import br.com.model.Notify;

public interface Publisher {

	void publish(Notify notify) throws Exception;
}
