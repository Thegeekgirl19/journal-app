package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "journalEntries")
@Data
@NoArgsConstructor
// equals getter setter requiredArgsConstructor toString equals&hashcode
public class JournalEntry
{
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;

	@Field
	@NonNull
	private String title;

	@Field
	private String content;

	@Field
	private LocalDateTime date;

}
