package pl.jaromin.chat1b.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.jaromin.chat1b.message.dto.ChannelHistoryDto;
import pl.jaromin.chat1b.message.dto.ChannelMessageDto;
import pl.jaromin.chat1b.message.dto.PrivateMessageDto;
import pl.jaromin.chat1b.message.dto.PublicMessageDto;
import pl.jaromin.chat1b.message.entity.ChatMessage;

@Mapper
public interface ChatMessageMapper {

    ChatMessageMapper INSTANCE = Mappers.getMapper(ChatMessageMapper.class);

    @Mapping(source = "sender", target = "sender.name")
    ChatMessage mapPublicToEntity (PublicMessageDto publicMessageDto);

    @Mapping(source = "sender", target = "sender.name")
    @Mapping(source = "receiver", target = "receiver.name")
    ChatMessage mapPrivateToEntity (PrivateMessageDto privateMessageDto);

    @Mapping(source = "sender", target = "sender.name")
    @Mapping(source = "channel", target = "channel.name")
    ChatMessage mapChannelToEntity (ChannelMessageDto channelMessageDto);

    @Mapping(target = "sender", source = "sender.name")
    @Mapping(target = "channel", source = "channel.name")
    ChannelHistoryDto mapEntityToHistory(ChatMessage message);
}
