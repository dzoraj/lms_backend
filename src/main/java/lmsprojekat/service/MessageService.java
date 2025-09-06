package lmsprojekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.MessageDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.Message;
import lmsprojekat.model.users.RegisteredUser;
import lmsprojekat.repository.FileRepository;
import lmsprojekat.repository.MessageRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.userrepo.RegisteredUserRepository;

@Service
public class MessageService extends AbstractCrudService<MessageDTO, Message, Long>{

	private final MessageRepository messageRepository;
	private final RegisteredUserRepository userRepository;
	private final FileRepository fileRepository;

	public MessageService(MessageRepository messageRepository, RegisteredUserRepository userRepository, FileRepository fileRepository) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
		this.fileRepository = fileRepository;
	}

	@Override
	protected SoftDeleteRepository<Message, Long> getRepository() {
		return messageRepository;
	}

	@Override
	protected MessageDTO toDTO(Message message) {
		MessageDTO dto = new MessageDTO();
		dto.setId(message.getId());
		dto.setDateSent(message.getDateSent());
		dto.setContent(message.getContent());
		dto.setSenderId(message.getSender().getId());
		dto.setReceiverId(message.getReceiver().getId());
		
		if (message.getAttachments() != null && !message.getAttachments().isEmpty()) {
	        List<Long> attachmentIds = message.getAttachments().stream()
	            .map(File::getId)
	            .collect(Collectors.toList());
	        dto.setAttachmentIds(attachmentIds);
	    } else {
	        dto.setAttachmentIds(new ArrayList<>());
	    }
		
		return dto;
	}

	@Override
	protected Message toEntity(MessageDTO dto) {
		Message message = new Message();
		message.setId(dto.getId());
		message.setDateSent(dto.getDateSent());
		message.setContent(dto.getContent());
		
		RegisteredUser sender = userRepository.findById(dto.getSenderId())
				.orElseThrow(() -> new EntityNotFoundException("Sender not found with ID: " + dto.getSenderId()));
		message.setSender(sender);
		
		RegisteredUser receiver = userRepository.findById(dto.getReceiverId())
				.orElseThrow(() -> new EntityNotFoundException("Receiver not found with ID: " + dto.getReceiverId()));
		message.setReceiver(receiver);
		
		if (dto.getAttachmentIds() != null && !dto.getAttachmentIds().isEmpty()) {
	        List<File> attachments = fileRepository.findAllById(dto.getAttachmentIds());
	        message.setAttachments(attachments);
	    } else {
	        message.setAttachments(new ArrayList<>());
	    }
		return message;
	}

	@Override
	protected void updateEntity(Message entity, MessageDTO dto) {
		entity.setDateSent(dto.getDateSent());
		entity.setContent(dto.getContent());
		
		RegisteredUser sender = userRepository.findById(dto.getSenderId())
				.orElseThrow(() -> new EntityNotFoundException("Sender not found with ID: " + dto.getSenderId()));
		entity.setSender(sender);
		
		RegisteredUser receiver = userRepository.findById(dto.getReceiverId())
				.orElseThrow(() -> new EntityNotFoundException("Receiver not found with ID: " + dto.getReceiverId()));
		entity.setReceiver(receiver);
		
		if (dto.getAttachmentIds() != null && !dto.getAttachmentIds().isEmpty()) {
	        List<File> attachments = fileRepository.findAllById(dto.getAttachmentIds());
	        entity.setAttachments(attachments);
	    } else {
	    	entity.setAttachments(new ArrayList<>());
	    }
	}
	
	
}
